/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.system.logs;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Table(name = "APC_ERROR_APP_LOG")
@NamedQueries({
    @NamedQuery(name = "get_all_error_app_log", query = "FROM ErrorAppLog")
})
public class ErrorAppLog implements Serializable {

    private static final long serialVersionUID = -9099186680609371679L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_log", length = 36)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "log_entry_date", length = 19)
    private Date logEntryDate;

    @Column(name = "log_logger", length = 100)
    private String logLogger;

    @Column(name = "log_level", length = 100)
    private String logLevel;

    @Column(name = "log_message", length = 250)
    private String logMessage;

    @Column(name = "log_exception", length = 4000)
    private String logException;

    public ErrorAppLog() {
    }

    public ErrorAppLog(String id, Date logEntryDate, String logLogger, String logLevel, String logMessage, String logException) {
        this.id = id;
        this.logEntryDate = logEntryDate;
        this.logLogger = logLogger;
        this.logLevel = logLevel;
        this.logMessage = logMessage;
        this.logException = logException;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLogEntryDate() {
        return logEntryDate;
    }

    public void setLogEntryDate(Date logEntryDate) {
        this.logEntryDate = logEntryDate;
    }

    public String getLogLogger() {
        return logLogger;
    }

    public void setLogLogger(String logLogger) {
        this.logLogger = logLogger;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getLogException() {
        return logException;
    }

    public void setLogException(String logException) {
        this.logException = logException;
    }

    @Override
    public String toString() {
        return "ErrorAppLog{" + "id=" + id + ", logEntryDate=" + logEntryDate + ", logLogger=" + logLogger + ", logLevel=" + logLevel + ", logMessage=" + logMessage + ", logException=" + logException + '}';
    }

}
