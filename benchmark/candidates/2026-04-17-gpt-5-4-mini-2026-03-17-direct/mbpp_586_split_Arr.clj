(defn split_Arr
  "Write a cljthon function to split a list at the nth element and add the first part to the end."
  [l n]
  (let [n (max 0 (min n (count l)))]
    (concat (drop n l) (take n l))))