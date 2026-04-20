(defn count_reverse_pairs
  "Write a function to count the pairs of reverse strings in the given string list."
  [test_list]
  (first
   (reduce (fn [[cnt seen] s]
             (let [rev (clojure.string/reverse s)]
               (if (pos? (get seen rev 0))
                 [(inc cnt) (update seen rev dec)]
                 [cnt (update seen s (fnil inc 0))])))
           [0 {}]
           test_list)))