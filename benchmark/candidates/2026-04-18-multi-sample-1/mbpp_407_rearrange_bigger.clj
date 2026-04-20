(defn rearrange_bigger
  "	Write a function to create the next bigger number by rearranging the digits of a given number."
  [n]
  (let [s (str n)
        digits (vec s)
        len (count digits)
        pivot (loop [i (- len 2)]
                (cond
                  (< i 0) nil
                  (< (compare (digits i) (digits (inc i))) 0) i
                  :else (recur (dec i))))]
    (if (nil? pivot)
      -1
      (let [pivot-digit (digits pivot)
            swap-idx (loop [i (dec len) best nil]
                       (if (<= i pivot)
                         best
                         (if (< (compare pivot-digit (digits i)) 0)
                           i
                           (recur (dec i) best))))
            swapped (-> digits
                        (assoc pivot (digits swap-idx))
                        (assoc swap-idx pivot-digit))
            prefix (subvec swapped 0 (inc pivot))
            suffix (subvec swapped (inc pivot))
            result-digits (into prefix (sort suffix))]
        (Long/parseLong (apply str result-digits))))))