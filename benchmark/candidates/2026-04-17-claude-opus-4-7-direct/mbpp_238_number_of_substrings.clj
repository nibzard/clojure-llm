(defn number_of_substrings
  "Count the number of non-empty substrings of a given string."
  [s]
  (if (string? s)
    (let [n (count s)]
      (/ (* n (inc n)) 2))
    0))