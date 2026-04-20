(defn median_of_many
  "Return the median of a collection of numbers.

  If the collection has odd length, return the middle value after sorting.
  If it has even length, return the average of the two middle values.

  Examples:
  (median_of_many [3 1 2])    ;=> 2
  (median_of_many [1 2 3 4])  ;=> 2.5
  (median_of_many nil)       ;=> nil
  (median_of_many [])        ;=> nil"
  [nums])