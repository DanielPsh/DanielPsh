

public class CThings
{
		private String name;
        private String colour;
		private String type;
		private int lens;
		

		public CThings()
		{
			name = null;
			colour = null;
			type = null; 
            this.lens = 0;
		}

		public CThings(String name, String colour, String type, int lens)
		{
			this.name = name;
			this.colour = colour;
			this.type = type; 
            this.lens = lens;
		}

		public String getName()
		{
			return this.name;
		}

		public String getColour()
		{
			return this.colour;
		}

		public String getType()
		{
			return this.type;
		}

        public int getLens()
        {
            return this.lens;
        }


		public void setName(String name)
		{
			this.name = name;
		}

		public void setColour(String colour)
		{
			this.colour = colour;
		}

		public void setType(String type)
		{
			this.type = type; 
		}
		
        public void setLens(int lens)
        {
            this.lens = lens;
        }
		
}
