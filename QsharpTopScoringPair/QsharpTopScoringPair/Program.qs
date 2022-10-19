
namespace Quantum.QsharpTopScoringPair  
{  
    // Import Quantum.Primitive  
    open Microsoft.Quantum.Canon;
    open Microsoft.Quantum.Intrinsic;  
    
    //open Microsoft.Quantum.Canon;
    //open Microsoft.Quantum.Intrinsic;
    open Microsoft.Quantum.Measurement;
    open Microsoft.Quantum.Math;
    open Microsoft.Quantum.Convert;

    operation SampleQuantumRandomNumberGenerator() : Result {
        // Allocate a qubit.
        use q = Qubit();
        // Put the qubit to superposition.
        H(q);
        // It now has a 50% chance of being measured 0 or 1.
        // Measure the qubit value.
        return M(q);
    }

    operation SampleRandomNumberInRange(max : Int) : Int {
        mutable output = 0; 
        repeat {
            mutable bits = []; 
            for idxBit in 1..BitSizeI(max) {
                set bits += [SampleQuantumRandomNumberGenerator()]; 
            }
            set output = ResultArrayAsInt(bits);
        } until (output <= max);
        return output;
    }

    //@EntryPoint()
    operation SampleRandomNumber() : Int {
        let max = 50;
        Message($"Sampling a random number between 0 and {max}: ");
        return SampleRandomNumberInRange(max);
    }


    // Create an operation that adds two int and returns a total int  
   
    operation HelloQ1 () : Result {
        Message("Hello quantum world!");
        use q = Qubit();
        H(q);
        return M(q);
    }



} 