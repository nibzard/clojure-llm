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
      -1
      (let [pivot-digit (digits pivot)
            swap-idx (loop [j (dec len)
                            best nil]
                       (if (<= j pivot)
                         best
                         (if (> (digits j) pivot-digit)
                           (if (or (nil? best) (< (digits j) (digits best)))
                             (recur (dec j) j)
                             (recur (dec j) best))
                           (recur (dec j) best))))
            swapped (assoc digits
                      pivot (digits swap-idx)
                      swap-idx pivot-digit)
            prefix (subvec swapped 0 (inc pivot))
            suffix (sort (subvec swapped (inc pivot)))]
        (Long/parseLong (apply str (concat prefix suffix)))))))