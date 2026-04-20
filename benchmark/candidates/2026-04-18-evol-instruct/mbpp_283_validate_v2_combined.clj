(defn validate
  "Given a collection of integers, return true if each number is valid for its
  own digits: the frequency of every digit in the number must be less than or
  equal to the digit itself.

  Ignore nil values in the collection.

  Examples:
  (validate [22 212 321]) => true
  (validate [11 12 20]) => false
  (validate [nil 22 nil]) => true"
  [nums]
  (letfn [(valid-number? [n]
            (let [digits (map #(Character/digit ^char % 10) (str (Math/abs (long n))))
                  freqs (frequencies digits)]
              (every? (fn [[d c]] (<= c d)) freqs)))]
    (every? valid-number? (remove nil? nums))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (validate [22 212 321])))
  (is (= false (validate [11 12 20])))
  (is (= true (validate [nil 22 nil]))))

(run-test test-variation)
