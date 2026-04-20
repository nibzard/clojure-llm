(defn split_Arr
  "Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [xs (seq l)
        cnt (count xs)
        k (if (pos? cnt) (mod n cnt) 0)]
    (concat (drop k xs) (take k xs))))