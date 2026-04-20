(defn sort_numeric_strings
  "	Write a function to sort a given list of strings of numbers numerically. https://www.geeksforgeeks.org/cljthon-sort-numeric-strings-in-a-list/"
  [nums_str]
  (letfn [(parse-num [s]
            (cond
              (nil? s) nil
              (number? s) s
              :else (bigdec (str s))))]
    (sort-by parse-num nums_str)))