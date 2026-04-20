(defn safe-rounded-avg
  "Given a possibly empty collection of numbers, compute the average of the
  values, round to the nearest integer, and return it as a binary string
  prefixed with \"0b\".

  Requirements:
  - Ignore nil values in the collection.
  - If the collection contains no numeric values after removing nils, return nil.
  - The input can be any sequence type, including infinite sequences, but your
  function will only be tested with finite inputs.
  - Use Math/round for rounding.
  
  Examples:
  >>> (safe-rounded-avg [1 2 3 4])
  \"0b10\"
  >>> (safe-rounded-avg [1 nil 5])
  \"0b11\"
  >>> (safe-rounded-avg [])
  nil
  >>> (safe-rounded-avg [10 11 12])
  \"0b1100\""
  [xs]
  (let [nums (remove nil? xs)]
    (when-let [s (seq nums)]
      (let [avg (/ (reduce + s) (count s))
            rounded (Math/round (double avg))]
        (str "0b" (Long/toBinaryString rounded))))))