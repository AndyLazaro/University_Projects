// Andy Lazaro
// 1001821751
// 10/04/2022


//#1 creating a array to hold values 1-10
const inputtable = [1,2,3,4,5,6,7,8,9,10];


//#2 creating new tables that are multiples of 5,13 and the squares from the 
//	 original inputtable
const fivetable = inputtable.map(x => x*5);
const thirteentable = inputtable.map(x => x*13);
const squaretable = inputtable.map(x => x**2);

console.log(fivetable);
console.log(thirteentable);
console.log(squaretable);

//#3
const OneHundredArr = Array.from(Array(100).keys()); //creating an array with values 0-99 
const NewArr = OneHundredArr.map(x => x+1);			//Making the array 1-100 by adding 1
const FilteredArr = NewArr.filter(IsOddOfFive);		//Filtering Array using conditional function
function IsOddOfFive(entry){	// Condititonal function to find where values are 
								//multiples of 5 and are odd
	if(entry % 5 ==0)
	{
		if(entry % 2 != 0)
		{
			return entry;
		}
	}
}
console.log(FilteredArr);

//4			Filtering the 1-100 array to only multiples of 7 that are even and then reducing the resulting array into 1 sum value of all entries
const EvenArr = OneHundredArr.filter(IsEvenOfSeven).reduce((previous,current) => previous + current);

function IsEvenOfSeven(entry)
{
	if(entry % 7 == 0)
		if(entry % 2 == 0)
			return entry;
}
console.log(EvenArr);

//#5

function cylinder_volume(r, h)
{ 
	var volume = 3.14 * r * r * h; 
	return volume; 
} 
// turns into CurriedVol through curring by changing the function to take 2 parameters 
// and immedietely return calculation without returning a variable

function CurriedVol(r){
	return (h) => {
		return 3.14 * r * r * h;
	}	
}
console.log(CurriedVol(5)(10));
console.log(CurriedVol(5)(17));
console.log(CurriedVol(5)(11));
//#6	makeTag takes 2 string to begin and end a field in HTML
//		such as "<tr>" , "</tr>" as the tags and then requires
//		text context in the middle, which we can fill with
//		copies of itself to create HTML Programs
makeTag = function(beginTag, endTag){ 
   return function(textcontent){ 
      return beginTag +textcontent +endTag; 
   } 
}
var Header = makeTag("<th>","</th>\n")("First_Name")	//create the headers of the table as entries
				+ makeTag("<th>","</th>\n")("Last_Name")
				+ makeTag("<th>","</th>\n")("Age");
				
var HeaderRow = makeTag("<tr>\n","</tr>\n")(Header);	//create a row for the headers

var RowContents1 = makeTag("<td>","</td>\n")("Andy")	//create the contents of row 1 in the table
				+ makeTag("<td>","</td>\n")("Lazaro")
				+ makeTag("<td>","</td>\n")("20");

var Row1 = makeTag("<tr>\n","</tr>\n")(RowContents1);	//create the row for the first contents

var RowContents2 = makeTag("<td>","</td>\n")("Crystal")	//create the contents of row 2 in the table
				+ makeTag("<td>","</td>\n")("Lara")
				+ makeTag("<td>","</td>\n")("21");
				
var Row2 = makeTag("<tr>\n","</tr>\n")(RowContents2);	//create the second row


console.log(makeTag("<table>\n","</table>\n")(HeaderRow + Row1 + Row2));

//#8

