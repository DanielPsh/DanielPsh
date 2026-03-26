

public class CBusiness
{
		private String name;
		private int year;

		public CBusiness()
		{
			name = null;
			year = 0;
		}

		public CBusiness(String name, int year)
		{
			this.name = name;
			this.year = year;
		}

		public String getName()
		{
			return this.name;
		}

		public int getYear ()
		{
			return this.year;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public void setYear(int year)
		{
			this.year = year;
		}
}
