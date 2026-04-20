(defn majority-index
  "Given a sorted vector of integers, return the zero-based index of the first occurrence of the majority element, or nil if no majority element exists.

  The majority element is the value that appears more than half the time in the vector.

  Examples:
  (majority-index [1 1 1 2 3])   ;; => 0
  (majority-index [1 2 2 2 3])   ;; => 1
  (majority-index [1 2 3 4])     ;; => nil
  (majority-index [])            ;; => nil"
  [v])