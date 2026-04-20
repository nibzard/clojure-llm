(defn problem-create-an-equation
  [& nums]
  (letfn [(combine [op args]
            (if (= 1 (count args))
              (first args)
              (apply list op args)))
          (build [nums]
            (if (= 1 (count nums))
              [{:expr (first nums) :val (first nums)}]
              (apply concat
                     (for [i (range 1 (count nums))]
                       (let [l (take i nums)
                             r (drop i nums)]
                         (for [le (build l)
                               re (build r)
                               :let [lop (if (seq? (:expr le)) (first (:expr le)) nil)
                                     rop (if (seq? (:expr re)) (first (:expr re)) nil)]
                               res (concat
                                    (when (= lop '+)
                                      [{:expr (combine '+ (concat (rest (:expr le)) [(:expr re)]))
                                        :val (+ (:val le) (:val re))}])
                                    (when (= lop '*)
                                      [{:expr (combine '* (concat (rest (:expr le)) [(:expr re)]))
                                        :val (* (:val le) (:val re))}])
                                    (when (and (not= lop '+) (not= rop '+))
                                      [{:expr (combine '+ [(:expr le) (:expr re)])
                                        :val (+ (:val le) (:val re))}])
                                    (when (and (not= lop '*) (not= rop '*))
                                      [{:expr (combine '* [(:expr le) (:expr re)])
                                        :val (* (:val le) (:val re))}]))]
                           res)))))]
    (let [lhs (butlast nums)
          rhs (last nums)]
      (some #(when (= (:val %) rhs) (list '= (:expr %) rhs))
            (build lhs)))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-create-an-equation
  (is (= (= (problem-create-an-equation 3 4 7) '(= (+ 3 4) 7))))
  (is (= (= (problem-create-an-equation 3 4 12) '(= (* 3 4) 12))))
  (is (= (= (problem-create-an-equation 3 4 14) nil)))
  (is (= (= (problem-create-an-equation 3 4 5 35) '(= (* (+ 3 4) 5) 35))))
  (is (= (= (problem-create-an-equation 3 4 5 60) '(= (+ (* 3 4) 5) 60))))
  (is (= (= (problem-create-an-equation 3 4 5 23) '(= (+ 3 (* 4 5)) 23))))
  (is (= (= (problem-create-an-equation 3 4 5 27) '(= (* 3 (+ 4 5)) 27))))
  (is (= (= (problem-create-an-equation 3 4 5 6) nil)))
  (is (= (= (problem-create-an-equation 1 2 10 100 2001) '(= (+ 1 (* 2 10 100)) 2001)))
  (is (= (= (problem-create-an-equation 1 2 10 100 1300) '(= (* (+ 1 2 10) 100) 1300))))

(run-tests)
