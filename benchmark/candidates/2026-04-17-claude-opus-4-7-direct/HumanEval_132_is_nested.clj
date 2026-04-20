(defn is_nested
  "Create a function that takes a string as input which contains only square brackets.
  The function should return true if and only if there is a valid subsequence of brackets 
  where at least one bracket in the subsequence is nested."
  [string]
  (let [pairs (map vector (re-seq #"\[" string) (re-seq #"\]" string))]
    (boolean (some (fn [[open close]] 
                     (and open close (< (count open) (count close)))) 
                   pairs))))