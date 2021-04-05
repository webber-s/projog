% corrected to verify fix for bug #198 - cyclic variable chains caused infinite loop

%QUERY X=X, numbervars(X)
%ANSWER X=$VAR(0)

%QUERY X=Y, Y=X, numbervars(X)
%ANSWER
% X=$VAR(0)
% Y=$VAR(0)
%ANSWER

%QUERY X=Y, Y=Z, Z=X, numbervars(X)
%ANSWER
% X=$VAR(0)
% Y=$VAR(0)
% Z=$VAR(0)
%ANSWER

%QUERY [A,B,C,D,E|F]=[E,A,D,F,C|B], numbervars(A)
%ANSWER
% A = $VAR(0)
% B = $VAR(0)
% C = $VAR(0)
% D = $VAR(0)
% E = $VAR(0)
% F = $VAR(0)
%ANSWER

%QUERY p(A,B,C,D,E,F)=p(E,A,D,F,C,B), numbervars(A)
%ANSWER
% A = $VAR(0)
% B = $VAR(0)
% C = $VAR(0)
% D = $VAR(0)
% E = $VAR(0)
% F = $VAR(0)
%ANSWER

%QUERY A=p(X,Y,Z), B=p(Z,X,Y), A=B, numbervars(A)
%ANSWER
% A = p($VAR(0), $VAR(0), $VAR(0))
% B = p($VAR(0), $VAR(0), $VAR(0))
% X = $VAR(0)
% Y = $VAR(0)
% Z = $VAR(0)
%ANSWER

%QUERY P1=p(Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Z,X,C,V,B,N,M), P2=p(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z), P1=P2, numbervars(P1)
%ANSWER
% A = $VAR(0)
% B = $VAR(0)
% C = $VAR(0)
% D = $VAR(0)
% E = $VAR(0)
% F = $VAR(1)
% G = $VAR(0)
% H = $VAR(0)
% I = $VAR(0)
% J = $VAR(0)
% K = $VAR(0)
% L = $VAR(2)
% M = $VAR(0)
% N = $VAR(1)
% O = $VAR(0)
% P = $VAR(0)
% P1 = p($VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(1), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(2), $VAR(0), $VAR(1), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(2), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(1), $VAR(0))
% P2 = p($VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(1), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(2), $VAR(0), $VAR(1), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(2), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(0), $VAR(1), $VAR(0))
% Q = $VAR(0)
% R = $VAR(0)
% S = $VAR(2)
% T = $VAR(0)
% U = $VAR(0)
% V = $VAR(0)
% W = $VAR(0)
% X = $VAR(0)
% Y = $VAR(1)
% Z = $VAR(0)
%ANSWER

