(defn problem-function-composition
  [& fs]
  (fn [& args]
    (let [rfs (reverse fs)]
      (reduce (fn [acc f] (f acc))
              (apply (first rfs) args)
              (rest rfs)))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-function-composition
  (is (= (= [3 2 1] ((problem-function-composition rest reverse) [1 2 3 4]))))
  (is (= (= 5 ((problem-function-composition (partial + 3) second) [1 2 3 4]))))
  (is (= (= true ((problem-function-composition zero? #(mod % 8) +) 3 5 7 9))))
  (is (= (= "HELLO" ((problem-function-composition #(.toUpperCase %) #(apply str %) take) 5 "hello world")))))

(run-tests)
