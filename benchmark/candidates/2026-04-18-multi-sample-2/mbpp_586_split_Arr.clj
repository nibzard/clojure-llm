(defn split_Arr
  "Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [xs (or l '())
        cnt (count xs)
        k   (cond
              (zero? cnt) 0
              (nil? n) 0
              :else (mod n cnt))]
    (concat (drop k xs) (take k xs))))