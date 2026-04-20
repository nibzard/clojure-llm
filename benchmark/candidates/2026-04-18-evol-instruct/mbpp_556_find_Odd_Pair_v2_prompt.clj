(defn count-odd-xor-pairs
  "Return the number of unordered pairs of values in `xs` whose bitwise xor is odd.

  Works with any sequence of integers, including nil and infinite sequences
  when only a finite prefix is needed via `take`.

  Examples:
  (count-odd-xor-pairs [1 2 3 4])
  ;;=> 4

  (count-odd-xor-pairs nil)
  ;;=> 0"
  [xs])