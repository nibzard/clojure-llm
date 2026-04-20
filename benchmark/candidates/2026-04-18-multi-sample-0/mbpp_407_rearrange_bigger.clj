(defn rearrange_bigger
  "	Write a function to create the next bigger number by rearranging the digits of a given number."
  [n]
  (let [s (str n)
        digits (vec s)
        len (count digits)]
    (loop [i (- len 2)]
      (if (< i 0)
        -1
        (if (< (compare (digits i) (digits (inc i))) 0)
          (let [pivot (digits i)
                suffix (subvec digits (inc i))
                swap-digit (apply min (filter #(pos? (compare % pivot)) suffix))
                swap-idx (+ (inc i)
                            (first (keep-indexed (fn [idx ch] (when (= ch swap-digit) idx)) suffix)))
                swapped (-> digits
                            (assoc i swap-digit)
                            (assoc swap-idx pivot))
                prefix (subvec swapped 0 (inc i))
                sorted-suffix (vec (sort (subvec swapped (inc i))))
                result-digits (into prefix sorted-suffix)]
            (Long/parseLong (apply str result-digits)))
          (recur (dec i)))))))