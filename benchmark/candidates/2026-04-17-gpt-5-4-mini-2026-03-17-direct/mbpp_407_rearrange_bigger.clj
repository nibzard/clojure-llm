(defn rearrange_bigger
  "	Write a function to create the next bigger number by rearranging the digits of a given number."
  [n]
  (let [digits (mapv #(Character/digit ^char % 10) (str n))
        len (count digits)]
    (loop [i (dec len)]
      (if (<= i 0)
        -1
        (if (< (digits (dec i)) (digits i))
          (let [pivot (dec i)
                suffix (subvec digits i)
                swap-idx (->> (range (count suffix))
                             (filter #(> (suffix %) (digits pivot)))
                             last)
                new-digits (-> (vec (take pivot digits))
                               (conj (suffix swap-idx))
                               (into (sort < (concat (subvec suffix 0 swap-idx)
                                                    (subvec suffix (inc swap-idx))))))]
            (Long/parseLong (apply str new-digits)))
          (recur (dec i)))))))