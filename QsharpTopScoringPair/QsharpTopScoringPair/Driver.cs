using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Quantum.Simulation.Simulators;
//using static Microsoft.Quantum.QsCompiler.SyntaxTokens.QsExpressionKind<Expr, Symbol, Type>;


using System;

using Microsoft.Quantum.Simulation.Core;

//using static Microsoft.Quantum.QsCompiler.SyntaxTokens.QsExpressionKind<Expr, Symbol, Type>;

namespace Quantum.QsharpTopScoringPair
{
    public class Driver
    {
        static void Main(string[] args)
        {
            Console.Out.WriteLine("testing c sharp and q sharp");
            var sim = new QuantumSimulator();
            var res = SampleRandomNumber.Run(sim).Result;
            Console.WriteLine(res);
            
            Console.WriteLine("Press any key to continue...");
            Console.ReadKey();

        }
    }
}

