package io.empirebank.jee.transfer.application.internal;

import io.empirebank.jee.transfer.domain.model.Transfer;
import io.empirebank.jee.transfer.domain.model.TransferRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.PersistenceException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceImplTest {

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferServiceImpl transferService = new TransferServiceImpl();


    @Test
    public void findAllShouldReturnEmptyListWhenNotFoundTransfers() {
        List transferList = Collections.EMPTY_LIST;
        when(transferRepository.findAll()).thenReturn(transferList);

        List<Transfer> actualList = transferService.findAll();
        verify(transferRepository).findAll();
        assertThat(actualList).isEmpty();
    }

    @Test
    public void findAllShouldReturnNonEmptyListWhenFoundTransfers() {
        List transferList = Collections.singletonList(new Transfer());
        when(transferRepository.findAll()).thenReturn(transferList);

        List<Transfer> actualList = transferService.findAll();
        assertThat(actualList.size()).isEqualTo(1);
    }

    @Test
    public void findByIdShouldReturnTransferWhenExists() {
        Transfer expectedTransfer = new Transfer();
        expectedTransfer.setId(1L);
        when(transferRepository.findBy(1L)).thenReturn(expectedTransfer);

        Transfer actualTransfer = transferService.findById(1L);

        assertThat(actualTransfer.getId()).isEqualTo(expectedTransfer.getId());
    }

    @Test
    public void findByIdShouldReturnNullWhenTransferNotFound() {
        Transfer expectedTransfer = null;
        when(transferRepository.findBy(1L)).thenReturn(expectedTransfer);

        Transfer actualTransfer = transferService.findById(1L);

        assertThat(actualTransfer).isNull();
    }

    @Test
    public void createTransferShouldReturnTransfer() {
        Transfer expectedTransfer = new Transfer();
        expectedTransfer.setId(2L);
        when(transferRepository.save(expectedTransfer)).thenReturn(expectedTransfer);

        Transfer actualTransfer = transferService.create(expectedTransfer);

        assertThat(actualTransfer.getId()).isEqualTo(expectedTransfer.getId());
        assertThat(actualTransfer).isEqualTo(expectedTransfer);
    }

    @Test(expected = PersistenceException.class)
    public void createTransferShouldThrowException() {
        Transfer expectedTransfer = new Transfer();
        expectedTransfer.setId(3L);
        when(transferRepository.save(expectedTransfer)).thenThrow(new PersistenceException());

        Transfer actualTransfer = transferService.create(expectedTransfer);
    }
}