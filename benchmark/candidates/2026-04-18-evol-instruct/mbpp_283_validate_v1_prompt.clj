(defn validate
  "Return true if every keyword in the map appears no more times in the values than its own numeric rank.

  A keyword's rank is determined by its position in the alphabet:
  :a => 1, :b => 2, ... :z => 26.

  Count only keyword values in the map; ignore nil and non-keyword values.

  Examples:
  (validate {:a [:b :b :c] :b [:a] :c nil}) ;=> true
  (validate {:a [:b :b :b] :b [:a]})       ;=> false
  (validate {:c [:a :a :a]})               ;=> true
  "
  [m])