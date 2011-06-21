package print;

//import java.awt.print.*;
import java.awt.print.Book;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.Serializable;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ReportDocument implements Serializable
{
    private static final float EXPAND_FACTOR = 1.5f;

	private int numOfReportPage;
	private int numOfRealPage;
    private int numOfMultiPage;

	private PageFormat pageFormat;
    private PageFormat multiPageFormat;

    private PCPageNumber pcPageNumber;
    private PCPageNumber pcDateTime;

	private ReportPage[] reportPage;
    private ReportMultiPage[] reportMultiPage;
    //private int multiPage;
    private int multiPageHor;
    private int multiPageVer;
    private int multiPageOrientation;
    private int multiPageDirection;
    private int multiPageBoarder;

    private PCHeaderFooter pCHeaderFooter;
    private String title = "";

    public ReportDocument()
    {
		pageFormat = new PageFormat();
        multiPageFormat = new PageFormat();
        pCHeaderFooter = new PCHeaderFooter(pageFormat);

		reportPage = new ReportPage[2];
        reportMultiPage = new ReportMultiPage[2];

        numOfReportPage = 0;
		numOfRealPage = 0;
        numOfMultiPage = 0;

        multiPageFormat = new PageFormat();

        Paper paper = multiPageFormat.getPaper();
        paper.setImageableArea(10,10, paper.getWidth() - 20, paper.getHeight() - 20);
        multiPageFormat.setPaper(paper);
        multiPageFormat.setOrientation(PageFormat.PORTRAIT);

        //TEMP
        this.setMultiPage(1, 1);
        this.setMultiPageDirection(DialogMultiPage.DIRECTION_ACCROSS);
        this.setMultiPageBoarder(DialogMultiPage.BOARDER_OFF);
        this.setMultiPageOrientation(PageFormat.PORTRAIT);

	}
    public void setTitle ( String title )
    {
        this.title = title;
    }
    public String getTitle ()
    {
        return title;
    }
    public PCHeaderFooter getHeaderFooter ()
    {
        return this.pCHeaderFooter;
    }
	public void addReportPage ( ReportPage reportPage )
	{
		if ( numOfReportPage == this.reportPage.length )
			expandReportPage();

		this.reportPage[numOfReportPage] = reportPage;
        this.reportPage[numOfReportPage].setPageFormat(pageFormat);
        this.reportPage[numOfReportPage].setHeaderFooter(this.pCHeaderFooter);
		numOfReportPage ++;
	}

	public ReportPage getReportPage ( int reportPageNumber )
	{
		return reportPage[reportPageNumber];
	}
    public ReportMultiPage getReportMultiPage ( int pageNumber )
    {
        //updateMultiPage();
        return reportMultiPage[pageNumber];
    }

    public void refresh ()
    {
        updatePageFormat();
        updateMultiPage();
        //updatePageAndTime();
    }
	private void expandReportPage()
   	{
      	int newSize = (int) (reportPage.length * EXPAND_FACTOR);

      	ReportPage tempReportPage[] = new ReportPage[newSize];
      	for (int i = 0; i < reportPage.length; i++)
      	{
        	tempReportPage[i] = reportPage[i];
      	}

      	reportPage = tempReportPage;
   	}
    private void expandReportMultiPage()
   	{
      	int newSize = (int) (reportMultiPage.length * EXPAND_FACTOR);

      	ReportMultiPage tempReportMultiPage[] = new ReportMultiPage[newSize];
      	for (int i = 0; i < reportMultiPage.length; i++)
      	{
        	tempReportMultiPage[i] = reportMultiPage[i];
      	}

      	reportMultiPage = tempReportMultiPage;
   	}
   	public int getNumOfReportPage()
   	{
   		return numOfReportPage;
   	}
    public int getNumOfPage()
    {
        return numOfMultiPage;
    }
    public void setPageFormat ( boolean auto )
    {
        pageFormat.setPaper(pageFormat.getPaper());
        pageFormat.setOrientation(pageFormat.getOrientation());

        Paper paper = (Paper)pageFormat.getPaper().clone();
        paper.setImageableArea(10,10, paper.getWidth() - 20, paper.getHeight() - 20);
        multiPageFormat.setPaper(paper);
        if ( multiPageVer * multiPageHor == 1 )
            multiPageFormat.setOrientation(pageFormat.getOrientation());
    }
   	public void setPageFormat ( )
   	{
		PrinterJob printJob = PrinterJob.getPrinterJob();
		java.awt.print.PageFormat newFormat =  printJob.pageDialog (pageFormat);
        pageFormat.setPaper(newFormat.getPaper());
        pageFormat.setOrientation(newFormat.getOrientation());

        Paper paper = (Paper)pageFormat.getPaper().clone();
        paper.setImageableArea(10,10, paper.getWidth() - 20, paper.getHeight() - 20);
        multiPageFormat.setPaper(paper);
        if ( multiPageVer * multiPageHor == 1 )
            multiPageFormat.setOrientation(pageFormat.getOrientation());
		//updatePageFormat();
	}
    public void prepareImage ()
    {
         System.out.println("Num Of Report Page: " + numOfReportPage);
        for ( int i = 0 ; i < numOfReportPage ; i ++ )
        {
            reportPage[i].getDiagram()[0].getObjectComponent().nullifyImage();
        }
    }
    public void restoreImage ()
    {
        for ( int i = 0 ; i < numOfReportPage ; i ++ )
        {
            reportPage[i].getDiagram()[0].getObjectComponent().restoreImage();
        }
    }
   	public void updateStartPage ( boolean preview )
   	{
   		int startPage = 0;
   		numOfRealPage = 0;
   		int i ;

   		for ( i = 0 ; i < numOfReportPage ; i ++ )
			numOfRealPage = numOfRealPage + reportPage[i].getNumOfPage();

		for ( i = 0 ; i < numOfReportPage ; i ++ )
		{
			reportPage[i].setTotalPageNumber(numOfRealPage);
			reportPage[i].setStartPageNumner(startPage);
			startPage = startPage + this.reportPage[i].getNumOfPage();
            reportPage[i].setPreview(preview);
		}
        updatePageFormat ();
        //updateMultiPage();
   	}
    public void updatePageFormat ()
    {
        for ( int i = 0 ; i < numOfReportPage ; i ++ )
        {
            reportPage[i].setPageFormat(pageFormat);
            reportPage[i].setPageNumberFormat(pcPageNumber);
            reportPage[i].setDateTimeFormat(pcDateTime);
            reportPage[i].setPageUpdate(true);
        }
   	}
   	public PageFormat getPageFormat()
   	{
   		return pageFormat;
   	}
    public PageFormat getMultiPageFormat ()
    {
        return multiPageFormat;
    }
    public void setPageNumberFormat ( PCPageNumber pcPageNumber )
    {
        this.pcPageNumber = pcPageNumber;
    }
    public PCPageNumber getPageNumberFormat()
    {
        return pcPageNumber;
    }
    public void setDateTimeFormat ( PCPageNumber pcPageNumber )
    {
        this.pcDateTime = pcPageNumber;
    }
    public PCPageNumber getDateTimeFormat ()
    {
        return pcDateTime;
    }
    /*
    public void setMuultipage ( int i )
    {
        multiPage = i;
    }*/
    public void setMultiPage ( int hor, int ver )
    {
        multiPageHor = hor;
        multiPageVer = ver;
        //updateMultiPage();
    }
    public void setMultiPageDirection ( int direction )
    {
        multiPageDirection = direction;
    }
    public void setMultiPageBoarder ( int boarder )
    {
        multiPageBoarder = boarder;
    }
    public void setMultiPageOrientation ( int orientation )
    {
        multiPageFormat.setOrientation(orientation);
    }
    /*
    public void updatePageAndTime ()
    {
        Book book = new Book();

        for ( int i = 0 ; i < numOfReportPage ; i ++ )
            book.append(reportPage[i], new PageFormat(), reportPage[i].getNumOfPage());
        for ( int i = 0 ; i < book.getNumberOfPages() ; i ++ )
        {
            //reportPage[i].updatePageNumberAndTime(i + 1, pageFormat);
        }
    }*/
    private void updateMultiPage()
    {
        //for ( int i = 0 ; i < numOfReportPage ; i ++ )
            //for ( int j = 0 ; j < reportPage[i].getNumOfPage() ; j ++ )
        //ReportPage[] reportPage =  new ReportPage[1];
        //reportPage[0] = this.reportPage[0];
        numOfMultiPage = 0;
        reportMultiPage = new ReportMultiPage[2];

        int pagePerPage = multiPageHor * multiPageVer;

        Book book = new Book();

        for ( int i = 0 ; i < numOfReportPage ; i ++ )
            book.append(reportPage[i], pageFormat, reportPage[i].getNumOfPage());

        for ( int i = 0 ; i < numOfRealPage;  i = i + pagePerPage )
        {
            if ( numOfMultiPage == reportMultiPage.length )
                expandReportMultiPage();

            ReportPage[] reportPage = new ReportPage[pagePerPage];
            for ( int j = 0 ; j < pagePerPage ; j ++ )
            {
                if ( i + j < numOfRealPage )
                {
                    reportPage[j] = (ReportPage) book.getPrintable(i + j);
                    //reportPage[j].updatePageNumberAndTime(i + j, pageFormat);
                }
            }
            reportMultiPage[numOfMultiPage] = new ReportMultiPage( reportPage, multiPageHor, multiPageVer, multiPageDirection, multiPageBoarder );
            numOfMultiPage ++;
        }

    }
}