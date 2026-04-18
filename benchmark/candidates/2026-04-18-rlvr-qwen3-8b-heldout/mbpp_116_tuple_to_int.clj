(defn tuple_to_int
  "	Write a function to convert a given vector of positive integers into a single integer."
  [nums]
  (let [digits (map #(Character/digit ^char % 10) (str (apply str nums)))]
    (bigint (reduce #(* 10 %1 (+ %2 0)) 0 digits))))