(defn test_three_equal
  "	Write a cljthon function to count the number of equal numbers from three given integers."
  [x y z]
  (apply max (vals (frequencies [x y z]))))