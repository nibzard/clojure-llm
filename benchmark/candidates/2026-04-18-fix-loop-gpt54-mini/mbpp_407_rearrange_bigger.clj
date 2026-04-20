(defn rearrange_bigger
  "	Write a function to create the next bigger number by rearranging the digits of a given number."
  [n]
  (let [digits (vec (map #(- (int %) 48) (str n)))
        len (count digits)
        i (loop [idx (- len 2)]
            (cond
              (< idx 0) -1
              (< (digits idx) (digits (inc idx))) idx
              :else (recur (dec idx))))]
    (if (= i -1)
      -1
      (let [pivot (digits i)
            j (loop [idx (dec len)]
                (if (> (digits idx) pivot)
                  idx
                  (recur (dec idx))))
            swapped (assoc digits i (digits j) j pivot)
            prefix (subvec swapped 0 (inc i))
            suffix (subvec swapped (inc i))
            result-digits (concat prefix (sort suffix))]
        (Long/parseLong (apply str result-digits))))))