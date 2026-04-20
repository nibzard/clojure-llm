(defn count_reverse_pairs
  "	Write a function to count the pairs of reverse strings in the given string list. https://www.geeksforgeeks.org/cljthon-program-to-count-the-pairs-of-reverse-strings/"
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (->> freqs
         (reduce
          (fn [{:keys [count seen]} [s n]]
            (let [rs (apply str (reverse s))]
              (cond
                (contains? seen s)
                {:count count :seen seen}

                (= s rs)
                {:count (+ count (quot n 2))
                 :seen (conj seen s)}

                :else
                {:count (+ count (min n (get freqs rs 0)))
                 :seen (conj seen s rs)})))
          {:count 0 :seen #{}})
         :count)))