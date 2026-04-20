(defn test_three_equal
  "	Write a cljthon function to count the number of equal numbers from three given integers."
  [x y z]
  (let [freqs (vals (frequencies [x y z]))]
    (cond
      (some #(= 3 %) freqs) 3
      (some #(= 2 %) freqs) 2
      :else 0)))