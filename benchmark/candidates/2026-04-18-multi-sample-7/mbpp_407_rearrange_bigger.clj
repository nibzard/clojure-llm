(defn rearrange_bigger
  "	Write a function to create the next bigger number by rearranging the digits of a given number."
  [n]
  (let [digits (mapv #(- (int %) (int \0)) (str n))
        len    (count digits)
        pivot  (loop [i (- len 2)]
                 (cond
                   (neg? i) nil
                   (< (digits i) (digits (inc i))) i
                   :else (recur (dec i))))]
    (if (nil? pivot)
      nil
      (let [pivot-digit (digits pivot)
            swap-idx    (loop [i (dec len)]
                          (if (> (digits i) pivot-digit)
                            i
                            (recur (dec i))))
            swapped     (-> digits
                            (assoc pivot (digits swap-idx))
                            (assoc swap-idx pivot-digit))
            prefix      (subvec swapped 0 (inc pivot))
            suffix      (sort (subvec swapped (inc pivot)))
            result      (reduce #(+ (* %1 10) %2) 0 (concat prefix suffix))]
        result))))