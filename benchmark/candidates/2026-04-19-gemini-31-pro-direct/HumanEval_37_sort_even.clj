(defn sort_even
  [l]
  (let [evens (sort (take-nth 2 l))
        odds (take-nth 2 (rest l))]
    (vec (take (count l) (interleave evens (concat odds [nil]))))))