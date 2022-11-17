# TCP vs UDP
| Basis          | TCP/Transmission Control Protocol                          | UDP/User Datagram Protocol                   |   |
|----------------|------------------------------------------------------------|----------------------------------------------|---|
| Service Type   | connection oriented;  establish/maintain/close connection; | datagram oriented; efficient for broardcast; |   |
| Reliability    | guarantee delivery                                         | No                                           |   |
| Error Checking | **flow control**; acknowledgement                          | checksum only                                |   |
| Sequence       | arrive in order                                            | no sequence                                  |   |
| Speed          | heavy, slow                                                | simple, fast, efficient                      |   |
| Retransmission | Yes                                                        | may lose data                                |   |
| Header         | 20-60 variable length                                      | 8 bytes fixed                                |   |
| Broadcasting   | No                                                         | Yes                                          |   |
| Protocol       | HTTP, FTP, SMTP, Telnet                                    | DNS, DHCP, SNMP, TFTP, RIP, VoIP             |   |
| Stream Type    | byte stream                                                | message stream                               |   |

# Refs
- https://www.geeksforgeeks.org/differences-between-tcp-and-udp/#:~:text=TCP%20is%20comparatively%20slower%20than,and%20more%20efficient%20than%20TCP.&text=Retransmission%20of%20lost%20packets%20is,User%20Datagram%20Protocol%20(UDP).
