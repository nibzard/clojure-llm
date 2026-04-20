(defn by_length
  "Given a vector of integers, sort the integers that are between 1 and 9 inclusive,
  reverse the resulting vector, and then replace each digit by its corresponding name from
  \"One\", \"Two\", \"Three\", \"Four\", \"Five\", \"Six\", \"Seven\", \"Eight\", \"Nine\".
  For example:
  >>> (by_length [2 1 1 4 5 8 2 3])
  [\"Eight\" \"Five\" \"Four\" \"Three\" \"Two\" \"Two\" \"One\" \"One\"]
    If the vector is empty, return an empty vector:
  >>> (by_length [])
  []
    If the vector has any strange number ignore it:
  >>> (by_length [1 -1 55])
  [\"One\"]"
  [arr]
  (let [names {1 "One"
               2 "Two"
               3 "Three"
               4 "Four"
               5 "Five"
               6 "Six"
               7 "Seven"
               8 "Eight"
               9 "Nine"}]
    (->> arr
         (filter #(<= 1 % 9))
         sort
         reverse
         (mapv names))))