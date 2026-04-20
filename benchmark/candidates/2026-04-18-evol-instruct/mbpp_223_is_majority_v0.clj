(defn majority-element
  "Given a vector of values, return the majority element if one exists, otherwise nil.

  A majority element appears more than half the time in the collection.

  Examples:
  (majority-element [:a :b :a :a]) ;=> :a
  (majority-element [1 2 3 2 2])   ;=> 2
  (majority-element [1 2 3 4])     ;=> nil"
  [xs]
  (let [freqs (frequencies xs)
        threshold (/ (count xs) 2)]
    (some (fn [[v c]] (when (> c threshold) v)) freqs)))