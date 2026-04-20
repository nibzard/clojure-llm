(defn collatz-parity-counts
  "Given a positive integer n, return a map with the counts of even and odd
  numbers encountered in the Collatz sequence starting at n, including n itself
  and 1.

  The Collatz sequence is defined as:
  - if the current number is even, the next number is current / 2
  - if the current number is odd, the next number is 3 * current + 1

  Return a map of the form {:even x :odd y}.

  Rules:
  - Count every term in the sequence, including the starting number and 1.
  - If n is 1, return {:even 0 :odd 1}.
  - The function should work for arbitrarily large positive integers.

  Examples:
  (collatz-parity-counts 5)  => {:even 4 :odd 2}
  ;; Sequence: 5, 16, 8, 4, 2, 1

  (collatz-parity-counts 1)  => {:even 0 :odd 1}

  (collatz-parity-counts 6)  => {:even 4 :odd 2}
  ;; Sequence: 6, 3, 10, 5, 16, 8, 4, 2, 1"
  [n]
  (let [step (fn [x] (if (even? x) (/ x 2) (inc (* 3 x))))
        seqv (take-while identity (iterate step n))
        seqv (concat seqv [1])]
    (reduce (fn [m x]
              (update m (if (even? x) :even :odd) inc))
            {:even 0 :odd 0}
            seqv)))