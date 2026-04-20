(defn check_greater
  "	Write a function to check whether the entered number is greater than the elements of the given vector."
  [arr number]
  (every? #(< % number) arr))