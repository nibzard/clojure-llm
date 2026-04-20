(defn is-strictly-divisible-by-11?
  "Return true if n is divisible by 11, false otherwise.

  Accepts integers, BigInts, and numeric strings.
  Examples:
  (is-strictly-divisible-by-11? 22)   ;=> true
  (is-strictly-divisible-by-11? \"23\") ;=> false
  (is-strictly-divisible-by-11? nil)   ;=> false"
  [n]
  (boolean
    (when-let [x (cond
                   (nil? n) nil
                   (string? n) (try (Long/parseLong n) (catch Exception _ nil))
                   :else n)]
      (zero? (mod x 11)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is-strictly-divisible-by-11? 22))))

(run-test test-variation)
