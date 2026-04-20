(defn split_Arr
  "Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [n (mod n (count l))]
    (concat (drop n l) (take n l))))