package de.telran_yefralex.BankAppProject.mapper;

import de.telran_yefralex.BankAppProject.dto.TransactionDto;
import de.telran_yefralex.BankAppProject.entity.Transaction;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Mapper(componentModel="spring")
public interface TransactionMapper  {
    @Named("toTransactionDto")
    @Mapping(source = "debitAccountId.accountNumber", target = "debitAccountNumber")
    @Mapping(source = "creditAccountId.accountNumber", target = "creditAccountNumber")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapToDate")
    TransactionDto toTransactionDto(Transaction transaction);


    Transaction toTransaction(TransactionDto transactionDto);

    @IterableMapping(qualifiedByName = "toTransactionDto")
    List<TransactionDto> mapToListDto(List<Transaction> transactions);

    @Named("mapToDate")
    default Date mapToDate(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }
}
