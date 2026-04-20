(defn xor-bytes
  "Given two byte arrays of equal length, return a new byte array where each
  position is the bitwise XOR of the corresponding bytes.

  If either input is nil, treat it as an empty byte array.
  If the arrays have different lengths, XOR up to the shorter length.

  Examples:
  (xor-bytes (byte-array [1 2 3]) (byte-array [4 5 6]))
  => (byte-array [5 7 5])

  (xor-bytes nil (byte-array [1 2]))
  => (byte-array [])

  [a b]
  (let [a (or a (byte-array []))
        b (or b (byte-array []))
        len (min (alength a) (alength b))]
    (byte-array
      (map (fn [x y] (byte (bit-xor x y)))
           (take len a)
           (take len b)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= (byte-array [5 7 5])
         (xor-bytes (byte-array [1 2 3]) (byte-array [4 5 6]))))
  (is (= (byte-array [])
         (xor-bytes nil (byte-array [1 2]))))
  (is (= (byte-array [7])
         (xor-bytes (byte-array [2 9]) (byte-array [5])))))

(run-test test-variation)
