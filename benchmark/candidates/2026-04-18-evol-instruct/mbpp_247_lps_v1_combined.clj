(defn longest-palindromic-prefix-len
  "Return the length of the longest palindromic prefix of `s`.

  Works with strings or any sequence of characters.

  Examples:
  (longest-palindromic-prefix-len \"racecarxyz\") ;=> 7
  (longest-palindromic-prefix-len \"abac\")       ;=> 3
  (longest-palindromic-prefix-len \"abc\")        ;=> 1
  (longest-palindromic-prefix-len nil)            ;=> 0"
  [s]
  (let [xs (seq s)]
    (if (nil? xs)
      0
      (let [v (vec xs)
            n (count v)]
        (loop [i n]
          (if (zero? i)
            0
            (let [prefix (subvec v 0 i)]
              (if (= prefix (vec (reverse prefix)))
                i
                (recur (dec i))))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (longest-palindromic-prefix-len "racecarxyz")))
  (is (= 3 (longest-palindromic-prefix-len "abac")))
  (is (= 1 (longest-palindromic-prefix-len "abc")))
  (is (= 0 (longest-palindromic-prefix-len nil))))

(run-test test-variation)
