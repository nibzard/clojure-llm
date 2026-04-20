(defn by_length
  "Given a vector of integers, sort the integers that are between 1 and 9 inclusive,
  reverse the resulting vector, and then replace each digit by its corresponding name from
  "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"."
  [arr]
  (let [digit-names ["One" "Two" "Three" "Four" "Five" "Six" "Seven" "Eight" "Nine"]
        valid-digits (filter #(and (integer? %) (<= 1 % 9)) arr)]
    (->> valid-digits
         sort
         reverse
         (mapv #(nth digit-names (dec %))))))