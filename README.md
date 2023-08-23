* # **FinancialPortfolio**
     # Introduction
    <font size = 4>This project is the final project presentation of the 20th team of neuade, and the theme of the project is financial portfolio.</font>
      
    <font size = 4>We use Vue+SpringBoot+MySQL to manage portfolios of one or more financial products.The basic functionality the project should have.</font>

    &nbsp;
     # Module Function
     <font size = 4>The front-end page available for interaction. </font>
     <br>
     <font size = 4>Resful API interface available for debugging, and implement basic CRUD functions</font>

    &nbsp;
    # <span id = page1>Functional Modules and Diagram Design</span>
    <!-- The information of First Page  -->
    ## 1.Front Page(Information about Assets and Assets Distribution)
     ![page1](page1.png)
    ## Module Details
    <font size = 4>For this page.We need to provide some function modules about asset information and asset distribution.And make an intuitive and easy-to-understand asset holding pie chart and asset allocation chart based on the above functions.
    </font> 

    <font size = 4>Below we will explain in detail the functions and data of each module:
    </font>

     <font size = 3 >
     <font color ='#87CEFA'><b>1.Account information</b></font> : For display only.Does not contain user permission logic control. <br>
     <font color ='#87CEFA'><b>2.Asset Information and Asset Operation</b></font>: Used to  display current assets and consumption. And we will provide a button called 'Operation' to realize the function of capital injection and withdrawa.<br>
     <font color ='#87CEFA'><b>3.The product count of Holding</b></font>:A monitor used to display the number of holdings for various products.<br>
     <font color ='#87CEFA'><b>4.Holding product table</b></font>：The table used to display the information of all of  holding pruducts. In this table ,we need to provide two function buttons  <b>BUY</b> and <b>SELL</b><br>
     <font color ='#87CEFA'><b>5.Pie Chart</b></font>: The Pie Chart is used to visualize the allocation of assets. <br>
     <font color ='#87CEFA'><b>6.Line Chart</b></font>: The Line Chart is used to visualize the holding count of the product.<br>
     </font>
  
     
     <!-- The information of Secondly Page  -->
     ## 2.The Profit of Assets Page(Assets Profit Details and Growth Rate Search)
     ![page2](page2.png)
    ## Module Details
    <font size = 4>We will show our asset returns on this page. And use the chart to display the change of the rate of return of the selected product in the search line.
    </font> 

    <font size = 4>Below we will explain in detail the functions and data of each module:
    </font>

     <font size = 3 >
     <font color ='#87CEFA'><b>1.Profit Analysis Module</b></font> : For this module, the content of function module need to show our total profit from various products and provide the profit details after click the button. <br>
     <font color ='#87CEFA'><b>2.Search Module</b></font>: Used to  display the trend of Products Growth Rate. In the Search Line ,we can get a Line Chart about the Growth Rate after inputting some information about the products. <br>
     </font>

     <!-- The information of Thirdly Page  -->
     ## 3.Product Price Change Monitoring Module
     ![page3](page3.png)
    ## Module Details
    <font size = 4>The page need to give user a Product Price Change Line Chart to show the price change trend .It should include  search function and a holding list.  
    </font> 

    <font size = 4>Below we will explain in detail the functions and data of each module:
    </font>

     <font size = 3 >
     <font color ='#87CEFA'><b>1.Holding Products List</b></font> : For the price change line chart of holding products, a holding list should be provided to the user. To facilitate users to directly see the price curve of the products they own. <br>
     <font color ='#87CEFA'><b>2.Search Module</b></font>: For the display of other polylines, a search function should be provided, using the product ID or name as the search key to monitor the user price changes of all products. <br>
     </font>

     <!-- The information of Thirdly Page  -->
     ## 4.Products Market
     ![page4](page4.png)
    ## Module Details
    <font size = 4>The page need to give user a Products Market.  
    </font> 

    <font size = 4>Below we will explain in detail the functions and data of each module:
    </font>

     <font size = 3 >
     <font color ='#87CEFA'><b>Market Module</b></font> : In this module . Users can scan all of the product and the information of products . For the Operation Function ,they can only buy. <br>
     </font>

     &nbsp; 
     # Database Design

     <font size = 4 >Based on the above requirement analysis and data requirements, we think that designing three database tables can meet our needs.<br>

     The following is our tables for our project and we will explain the fields in detail.</font>

     1.<font size =4>RECORD TABLE</font>
     |  ID    | FIELD NAME   |  FIELD TYPE    |  NOT NUll |      PRIMARY KEY | 
     |  ----  | ----         |   ----         |   -----     |         ----     |  
     |  1     | ID       |      INT          |   YES      |    YES|
     |  2     | Funds_Name  |   VARCHAR(100) |   NO         |   NO|
     |  3     | Funds_Count  |   INT |   NO         |   NO|
     |  4     | Purchase_Time  |   DATE |   NO         |   NO|
     |  5     | Update_Time |   DATE |   NO         |   NO|
     |  6     | Purchase_Cost |   DOUBLE |   NO         |   NO|

     <font size = 3 ><font color="#87CEFA"><b>ID</b></font> : Identifier in the table.It is primary key in the RECORD</font>
     
     <font size = 3 ><font color="#87CEFA"><b>Funds_Name</b></font> : Identifier in the table.</font>

     <font size = 3 ><font color="#87CEFA"><b>Funds_Count</b></font> : Used to show the count of holding fund.It will change after operation 'BUY' or 'SELL'.</font>

     <font size = 3 ><font color="#87CEFA"><b>Purchase_Time</b></font> : When the fund was purchased for the first time. </font>

     <font size = 3 ><font color="#87CEFA"><b>Update_Time</b></font> : When the fund was operated for the last time..</font>

     <font size = 3 ><font color="#87CEFA"><b>Purchase_Cost</b></font> : The current cost price of buying the fund.</font>

     2.<font size =4>PRODUCT TABLE</font>
     |  ID    | FIELD NAME   |  FIELD TYPE    |  NOT NUll |      PRIMARY KEY | 
     |  ----  | ----         |   ----         |   -----     |         ----     |  
     |  1     | ID       |      INT          |   YES      |    YES|
     |  2     | Product_ID  |   INT |   NO         |   NO|
     |  3     | Date_Time  |   DATE |   NO         |   NO|
     |  4     | Current_Price  |   DOUBLE |   NO         |   NO|
     |  5     | Growth_Rate |   DOUBLE |   NO         |   NO|


     <font size = 3 ><font color="#87CEFA"><b>ID</b></font> : Identifier in the table.</font>
     
     <font size = 3 ><font color="#87CEFA"><b>Product_ID</b></font> :This field corresponds to the id in the record table. It is used to associate the relationship between the two tables.</font>

     <font size = 3 ><font color="#87CEFA"><b>Date_Time</b></font> : The current date of the fund.</font>

     <font size = 3 ><font color="#87CEFA"><b>Current_Price</b></font> : The price of the fund on date 'Date_Time'.</font>

     <font size = 3 ><font color="#87CEFA"><b>Growth_Rate</b></font> :The growth rate of the fund on date 'Date_Time'.</font>

    3.<font size =4>OPERATION TABLE</font>
     |  ID    | FIELD NAME   |  FIELD TYPE    |  NOT NUll |      PRIMARY KEY | 
     |  ----  | ----         |   ----         |   -----     |         ----     |  
     |  1     | ID       |      INT          |   YES      |    YES|
     |  2     | Operation_ProductID  |   INT |   NO         |   NO|
     |  3     | Operation_Type  |   TINYINT(1) |   NO         |   NO|
     |  4     | Operation_Time  |   DATE |   NO         |   NO|
     |  5     | Operation_Count |   DOUBLE |   NO         |   NO|
     |  6     | Current_Assets  |   DOUBLE |   NO         |   NO|


     <font size = 3 ><font color="#87CEFA"><b>ID</b></font> : Identifier in the table.</font>
     
     <font size = 3 ><font color="#87CEFA"><b>Operation_ProductID</b></font> :This field corresponds to the id in the record table. It is used to associate the relationship between the two tables.</font>

     <font size = 3 ><font color="#87CEFA"><b>Operation_Type</b></font> : We use different values 0,1,2,3 to represent different operation types . </font>
     |  Type  | Description |
     |  ----  | ----          |  
     |  0     | BUY           |  
     |  1     | SELL           |  
     |  2     | INJECTION           |  
     |  3     | WITHDRAW           |   

     <font size = 3 ><font color="#87CEFA"><b>Operation_Time</b></font> : The date time of the operation.</font>

     <font size = 3 ><font color="#87CEFA"><b>Operation_Count</b></font> :How much is the specific operation. If type is 0,1, it indicates how many funds are operated. type is 3, 4 means how much money was operated</font>

     <font size = 3 ><font color="#87CEFA"><b>Current_Assets</b></font> :Current Assets after operation.</font>

     # API Design 
     <font size = 4 >
     According to the previously designed pages, we design the corresponding API . Then introduce the relevant information of each page in the same format as in the <a href = '#page1'>Functional Modules and Diagram Design</a> module
     </font>
     
     ## 1.Front Page

     <font size = 4 >
     In this page ,the main function is to show the asset of the user and operate the funds of holding. So the page needs to have some basic functionlity like <font color ="#87CEFA"><b>BUY</b></font>  and <font color ="#87CEFA"><b>SELL</b></font>. We only allow users sell their own funds in this page ,so the purpose of designing first page is used to manage their  property.
     </font>
     
     <font size = 4 >
     In order to accomplish the functions . The following will show how to connect front-end and back-end by data. 
     </font>
 
     ## 1) Account Module 
     ### Used to show the current asset 
     |     API Address    |   /api/financialinfo/account    |
     |   ------     | :------:  |
     |Request Method|  HTTP/GET|
     |Function|show user's Asset  |

     ### Asset Injection
     |     API Address    |   /api/financialinfo/injection    |
     |   ------     | :------:  |
     |Request Method|  HTTP/POST|
     |Function|inject user's asset   |

     ### Asset Withdraw
     |     API Address    |   /api/financialinfo/withdraw    |
     |   ------     | :------:  |
     |Request Method|  HTTP/POST|
     |Function|withdraw user's asset   |

     ## 2) Holding Products Count
     ### Used to count the number of products held by users
     |     API Address    |   /api/financialinfo/count   |
     |   ------     | :------:  |
     |Request Method|  HTTP/GET|
     |Function|show the count of Holiding Products Count and draw a line chart |

     ## 3) Holding Funds Table
     ### Display the holding funds and provide <font color = "#87CEFA">BUY</font> and <font color = "#87CEFA">SELL</font> function
     |     API Address    |   /api/financialinfo/funds   |
     |   ------     | :------:  |
     |Request Method|  HTTP/GET|
     |Function|show informations of all of the holding funds   |

     ### Operation : BUY
     |     API Address    |   /api/financialinfo/buy   |
     |   ------     | :------:  |
     |Request Method|  HTTP/POST|
     |Function|show informations of all of the holding funds   |

     ### Operation : SELL
     |     API Address    |   /api/financialinfo/sell   |
     |   ------     | :------:  |
     |Request Method|  HTTP/POST|
     |Function|show informations of all of the holding funds   |

     ## 2.The Profit of Assets Page

     <font size = 4 >
     In this page ,the main function is to show the profit from funds or every fund.So we not only need to calculate funds' profit but also provide a line chart to show the changes in the returns of a fund over time.
     </font>
     
     <font size = 4 >
     In order to accomplish the functions . The following will show how to connect front-end and back-end by data. 
     </font>
 
     ## 1) Profit Display Module 
     ### Used to show the profit of all funds and every fund 
     |     API Address    |   /api/product/fundsprofit   |
     |   ------     | :------:  |
     |Request Method|  HTTP/GET|
     |Function|show the profits |

     ###  The Line Chart of the Growth Rate
     |     API Address    |   /api/product/allproducts/{fundsId}  |
     |   ------     | :------:  |
     |Request Method|  HTTP/POST|
     |Function|search the growth rate of one fund  by ID |
     

     ## 3.Product Price Change Monitoring Module

     <font size = 4 >
     In this page ,the main function is show the price change of the holding funds and unholding funds.
     </font>
     
     <font size = 4 >
     In order to accomplish the functions . The following will show how to connect front-end and back-end by data. 
     </font>
 
     ## 1) Profit Display Module 
     ### Used to show the profit of all funds and every fund 
     |     API Address    |   /api/financialinfo/funds  |
     |   ------     | :------:  |
     |Request Method|  HTTP/GET|
     |Function|get the holding funds list |

     ###  The Line Chart of the Growth Rate
     |     API Address    |   /api/product/fundsprice  |
     |   ------     | :------:  |
     |Request Method|  HTTP/GET|
     |Function|get fund price by searching ID  |
    
    ## 4.Market Module

     <font size = 4 >
     In this page ,the main function is show the information of the all funds.It is a maket , user can buy their funds from there.
     </font>
     
     <font size = 4 >
     In order to accomplish the functions . The following will show how to connect front-end and back-end by data. 
     </font>
 
     ## 1) Market Display 
     ### Used to show the information of the funds
     |     API Address    |   /api/product/allproducts |
     |   ------     | :------:  |
     |Request Method|  HTTP/GET|
     |Function|search the information of funds  by ID |
     

     ### Search Line Module
     |     API Address    |   /api/product/allproducts/{fundsId} |
     |   ------     | :------:  |
     |Request Method|  HTTP/GET|
     |Function|get  one fund information by searching ID  |

     ### Operation: BUY
     |     API Address    |   /api/financialinfo/buy  |
     |   ------     | :------:  |
     |Request Method|  HTTP/POST|
     |Function|Buy funds |
