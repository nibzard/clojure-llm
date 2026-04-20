(defn collatz-odds-count
  "Given a positive integer n, return the number of odd values encountered in the Collatz sequence
  starting from n, including n itself if it is odd and including 1.

  The Collatz sequence is defined as:
  - if the current number is even, the next number is half of it
  - if the current number is odd, the next number is 3 times it plus 1

  Constraints:
  - Return 0 for nil or non-positive input.
  - Use a lazy sequence somewhere in your solution.
  - You may assume n is an integer when provided.

  Examples:
  (collatz-odds-count 1) => 1
  (collatz-odds-count 5) => 2   ; sequence: 5 16 8 4 2 1
  (collatz-odds-count 6) => 2   ; sequence: 6 3 10 5 16 8 4 2 1
  (collatz-odds-count nil) => 0"
  [n]
  (if (and (integer? n) (pos? n))
    (count (filter odd? (take-while #(pos? %) (iterate (fn [x] (if (even? x) (/ x 2) (inc (* 3 x)))) n))))
    0))