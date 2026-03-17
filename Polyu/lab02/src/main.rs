struct CrabString (String);

impl Drop for CrabString{
    // drop function will be called when CabString Object is being destroyed
    fn drop(&mut self){
        println!(r"on no, my value: [{}] is being destroyed", self.0);
    }
}
fn main() {
    println!("PROGRAM STARTED");
    {
        let _s = CrabString(String::from("My name is ferris"));
    }
    println!("PROGRAM ENDED");


    let a = String::from("hi");
    let b = a.clone(); // moved to b
    println!("{}", a);
    
    std::mem::drop(a);
    
    let mut i = 10;
    let p = &i;
    let q = &i;
    // let r = &mut i;
    // cannot mix mutable and immutable references at the same time

    println!("{}", p);
    println!("{}", q);

    let x = 5; //immutable
    let mut y = 5; //mutable
    y = 6;
}

