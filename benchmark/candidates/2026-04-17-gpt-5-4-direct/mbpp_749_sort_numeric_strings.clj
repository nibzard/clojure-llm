(defn sort_numeric_strings
  "	Write a function to sort a given list of strings of numbers numerically. https://www.geeksforgeeks.org/cljthon-sort-numeric-strings-in-a-list/"
  [nums_str]
  (let [parse-num #(bigint %)]
    (if (nil? nums_str)
      nil
      (sort-by parse-num nums_str))))