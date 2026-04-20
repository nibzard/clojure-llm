(defn merge-with-precedence
  "Merge three maps into one.
   When the same key appears in multiple maps, the value from the later
   map wins (dict3 > dict2 > dict1).

   Examples:
   (merge-with-precedence {:a 1 :b 2} {:b 3 :c 4} {:a 9 :d 5})
   => {:a 9, :b 3, :c 4, :d 5}"
  [dict1 dict2 dict3])