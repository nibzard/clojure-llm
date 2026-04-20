(defn rearrange_bigger
  "	Write a function to create the next bigger number by rearranging the digits of a given number."
  [n]
  (let [digits (mapv #(- (int %) (int \0)) (str n))
        len (count digits)
        pivot (loop [i (- len 2)]
                (cond
                  (< i 0) nil
                  (< (digits i) (digits (inc i))) i
                  :else (recur (dec i))))]
    (if (nil? pivot)
      nil
      (let [pivot-digit (digits pivot)
            swap-idx (loop [i (dec len) best nil]
                       (if (<= i pivot)
                         best
                         (if (> (digits i) pivot-digit)
                           i
                           (recur (dec i) best))))
            swapped (-> digits
                        (assoc pivot (digits swap-idx))
                        (assoc swap-idx pivot-digit))
            prefix (subvec swapped 0 (inc pivot))
            suffix (sort (subvec swapped (inc pivot)))
            result-digits (concat prefix suffix)]
        (reduce (fn [acc d] (+ (* acc 10) d)) 0 result-digits)))))