# Language Test


## Shell
~~~shell
#!/bin/bash

echo "Hello, World!"

function greet() {
  echo "Hello, $1!"
}

greet "Shell"
~~~

## C
~~~c
#include <stdio.h>

void greet(const char* name) {
  printf("Hello, %s!\n", name);
}

int main() {
  printf("Hello, World!\n");
  greet("C");
  return 0;
}
~~~

## C++
~~~cpp
#include <iostream>
using namespace std;

class Greeter {
public:
  void greet(const string& name) {
    cout << "Hello, " << name << "!" << endl;
  }
};

int main() {
  cout << "Hello, World!" << endl;
  Greeter greeter;
  greeter.greet("C++");
  return 0;
}
~~~

## Java
~~~java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Greeter greeter = new Greeter();
        greeter.greet("Java");
    }
}

class Greeter {
    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
~~~

## Python
~~~python
class Greeter:
    def greet(self, name):
        print("Hello, {}!".format(name))

if __name__ == "__main__":
    print("Hello, World!")
    greeter = Greeter()
    greeter.greet("Python")
~~~

## Scala
~~~scala
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, World!")
    val greeter = new Greeter()
    greeter.greet("Scala")
  }
}

class Greeter {
  def greet(name: String): Unit = {
    println("Hello, " + name + "!")
  }
}
~~~

## Rust
~~~rust
struct Greeter;

impl Greeter {
    fn greet(&self, name: &str) {
        println!("Hello, {}!", name);
    }
}

fn main() {
    println!("Hello, World!");
    let greeter = Greeter;
    greeter.greet("Rust");
}
~~~

## PHP
~~~php
<?php
class Greeter {
  public function greet($name) {
    echo "Hello, $name!\n";
  }
}

echo "Hello, World!\n";
$greeter = new Greeter();
$greeter->greet("PHP");
?>
~~~

## JavaScript
~~~javascript
class Greeter {
  greet(name) {
    console.log(`Hello, ${name}!`);
  }
}

console.log("Hello, World!");
const greeter = new Greeter();
greeter.greet("JavaScript");
~~~

## Go
~~~go
package main

import "fmt"

type Greeter struct{}

func (g Greeter) Greet(name string) {
	fmt.Printf("Hello, %s!\n", name)
}

func main() {
	fmt.Println("Hello, World!")
	g := Greeter{}
	g.Greet("Go")
}
~~~

## Swift
~~~swift
class Greeter {
    func greet(name: String) {
        print("Hello, \(name)!")
    }
}

let greeter = Greeter()
print("Hello, World!")
greeter.greet(name: "Swift")
~~~

## Kotlin
~~~kotlin
class Greeter {
    fun greet(name: String) {
        println("Hello, $name!")
    }
}

fun main() {
    println("Hello, World!")
    val greeter = Greeter()
    greeter.greet("Kotlin")
}
~~~

## TypeScript
~~~typescript
class Greeter {
    greet(name: string) {
        console.log(`Hello, ${name}!`);
    }
}

console.log("Hello, World!");
const greeter = new Greeter();
greeter.greet("TypeScript");
~~~

## Ruby
~~~ruby
class Greeter
  def greet(name)
    puts "Hello, #{name}!"
  end
end

puts "Hello, World!"
greeter = Greeter.new
greeter.greet("Ruby")
~~~

## Objective-C
~~~objective-c
#import <Foundation/Foundation.h>

@interface Greeter : NSObject
- (void)greet:(NSString *)name;
@end

@implementation Greeter
- (void)greet:(NSString *)name {
    NSLog(@"Hello, %@!", name);
}
@end

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        NSLog(@"Hello, World!");
        Greeter *greeter = [[Greeter alloc] init];
        [greeter greet:@"Objective-C"];
    }
    return 0;
}
~~~

## VB.NET (Visual Basic .NET)
~~~vb
Imports System

Module Module1
    Sub Main()
        Console.WriteLine("Hello, World!")
        Dim greeter As New Greeter()
        greeter.Greet("VB.NET")
    End Sub
End Module

Class Greeter
    Public Sub Greet(name As String)
        Console.WriteLine("Hello, " + name + "!")
    End Sub
End Class
~~~

## Lua
~~~lua
Greeter = {}
Greeter.__index = Greeter

function Greeter:new()
   return setmetatable({}, Greeter)
end

function Greeter:greet(name)
   print("Hello, " .. name .. "!")
end

print("Hello, World!")
local greeter = Greeter:new()
greeter:greet("Lua")
~~~

## Perl
~~~perl
#!/usr/bin/perl
use strict;
use warnings;

package Greeter;
sub new {
    my $class = shift;
    my $self = {};
    bless $self, $class;
    return $self;
}

sub greet {
    my ($self, $name) = @_;
    print "Hello, $name!\n";
}

package main;
print "Hello, World!\n";
my $greeter = Greeter->new();
$greeter->greet("Perl");
~~~

## Haskell
~~~haskell
module Main where

main :: IO ()
main = do
  putStrLn "Hello, World!"
  greet "Haskell"

greet :: String -> IO ()
greet name = putStrLn $ "Hello, " ++ name ++ "!"
~~~

## Clojure
~~~clojure
(defn greet [name]
  (println (str "Hello, " name "!")))

(defn -main [& args]
  (println "Hello, World!")
  (greet "Clojure"))
~~~

## Groovy
~~~groovy
class Greeter {
    def greet(name) {
        println "Hello, ${name}!"
    }
}

def greeter = new Greeter()
println "Hello, World!"
greeter.greet("Groovy")
~~~

## F#
~~~fsharp
type Greeter() =
    member this.Greet(name: string) =
        printfn "Hello, %s!" name

[<EntryPoint>]
let main argv =
    printfn "Hello, World!"
    let greeter = Greeter()
    greeter.Greet("F#")
    0 // return an integer exit code
~~~

## Dart
~~~dart
class Greeter {
  void greet(String name) {
    print('Hello, $name!');
  }
}

void main() {
  print('Hello, World!');
  final greeter = Greeter();
  greeter.greet('Dart');
}
~~~

## C#
~~~csharp
using System;

namespace HelloWorldApp
{
    class Greeter
    {
        public void Greet(string name)
        {
            Console.WriteLine($"Hello, {name}!");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello, World!");
            Greeter greeter = new Greeter();
            greeter.Greet("C#");
        }
    }
}
~~~

## COBOL
~~~cobol
       IDENTIFICATION DIVISION.
       PROGRAM-ID. HELLO-WORLD.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 GREETING PIC X(20) VALUE 'Hello, World!'.
       01 NAME PIC X(20) VALUE 'COBOL'.
       
       PROCEDURE DIVISION.
           DISPLAY GREETING.
           CALL 'GREET' USING NAME.
           STOP RUN.

       IDENTIFICATION DIVISION.
       PROGRAM-ID. GREET.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 WELCOME-MESSAGE PIC X(30).
       
       LINKAGE SECTION.
       01 NAME PIC X(20).
       
       PROCEDURE DIVISION USING NAME.
           MOVE 'Hello, ' TO WELCOME-MESSAGE.
           STRING WELCOME-MESSAGE NAME DELIMITED BY SIZE INTO WELCOME-MESSAGE.
           DISPLAY WELCOME-MESSAGE.
           GOBACK.
~~~