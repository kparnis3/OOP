#include "myuint.hpp"
#include "functions.hpp"

void AdditionTests()
{
    myuint<1024> Add1("20");
    myuint<1024> Add2("3980");

    myuint<1024> Add3 = Add1 + Add2;
    cout<<"Addition of 3980 + 20 is: " << Add3 << endl;

    Add3 += Add1;

    cout<<"Adding 20 to previous answer: " << Add3 << endl;

    Add3 += 100;

    cout<<"Adding 100 to previous answer " << Add3 << endl;

    Add1 = Add1 + 100;
    
    cout <<"Adding 100 to 20: "<< Add1 << endl;

    myuint<8> k("1");
    myuint<8> Add4 = k++;
    cout << "Add4: " << Add4 << " k: " << k << endl;

    myuint<8> L("1");
    myuint<8> Add5 = ++L;
    cout << "Add5: " << Add5 << " L: " << L << endl;
}

void RalationalTests()
{
    myuint<4> a(5);
    myuint<4> b(5);

    if(a==b)
    {
        cout << a << " is equal to " << b << endl;
    }
    else
    {
        cout << "Result isn't equal" << endl;
    }


    myuint<1024> m("3213123123123173218");
    myuint<1024> y("3231243");

    if(m<y)
    {
        cout << y <<" is greater then "<< m << endl;
    }
    else if (m>y)
    {
        cout << m <<" is greater then "<< y <<endl;
    }
    else
    {
        cout << "Both are equal" << endl;
    }

    myuint<1024> u("32000");
    myuint<1024> o("2000");

    if(u>=o)
    {
        cout << u << " is greater or equal to " << o << endl;
    }
    else
    {
        cout << o <<" is greater then" << u << endl;
    }

    if(u!=o)
    {
         cout << u <<" is not equal to " << o << endl;
    }
    else
    {
        cout << u <<" is equal to " << o << endl;
    }
}

int foo()
{
    myuint<512> i(5);
    myuint<512> j = (i<<1000) + 23;
    return j.convert_to<int>();
}

void SubtractionTests()
{
 
    //Subtraction tests
    myuint<1024> u("320000000000000000000000");
    myuint<1024> o("20000000000000000000000");
    myuint<1024> f = u -o;

    cout << "Subtracting "<< o << " from " << u <<": "<< f << endl;

    myuint<64> sub1("40");
    sub1 = sub1 - 20;

    cout << "Subtracting 20 from 40: " << sub1 << endl;

    myuint<16> a(30000);
    myuint<16> b(2000);
    a -= 15000;
    a -= b;

    cout << "Subtracting 2000 and 15000 from 30000: "<< a << endl;

    myuint<2> n("2");
    myuint<2> sub2 = --n;
    cout << "sub2: " << sub2 << " n: " << n << endl;
    
    myuint<2> p("2");
    myuint<2> sub3 = p--;
    cout << "sub3: " << sub3 << " p: " << p << endl;
}

void MultiplicationTests()
{
    myuint<1024> multi1("33");
    myuint<1024> multi2("0");

    myuint<1024> multi3 = multi1 * multi2;

    cout << multi1 <<" * " << multi2 << ": " << multi3 << endl; 

    myuint<1024> multi4("22");
    myuint<1024> multi5("42");

    myuint<1024> multi6 = multi4 * multi5;

    cout << multi4 <<" * " << multi5 << ": " << multi6 << endl;
}

void DivisionTests()
{
    myuint<64> div1(100);

    myuint<64> div2 = div1 / 5;
    cout << "100 divided by 5 is " << div2 << endl;

    myuint<16> div4(20);
    div4 = div4 / 20; 
    cout << "20 divided by 20 is " <<  div4 << endl;

    myuint<4> div3(100);
    div3 = div3 / 0; 
    cout << div3 << endl;
}

void ModulusTests()
{
    myuint<8> mod1("625");
    myuint<8> mod2 = mod1 % 5;
    cout << "625 mod 5 is: " << mod2 << endl;

    myuint<1024> mod3(2000);
    myuint<1024> mod4 = mod3 % 12;
    cout << "2000 mod 12 is: " << mod4 << endl;

}


int main()
{ 
    cout<<foo()<<endl;

    AdditionTests();
    RalationalTests();
    //SubtractionTests(); //this test exists program
    MultiplicationTests();
    ModulusTests();
    DivisionTests(); //this test also exists program
    

    return 0;
    
}