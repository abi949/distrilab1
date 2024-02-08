package server;

import com.grpc.matrixmult.MatrixRequest;
import com.grpc.matrixmult.MatrixReply;
import com.grpc.matrixmult.MatrixServiceGrpc.MatrixServiceImplBase;

import io.grpc.stub.StreamObserver;

public class MatrixServiceImpl extends MatrixServiceImplBase {

    @Override
    public void addBlock(MatrixRequest request, StreamObserver<MatrixReply> replyObserver) {
        System.out.println("Request received for matrix addition:\n" + request);

        // Perform matrix addition for the specified block
        int C00 = request.getA00() + request.getB00();
        int C01 = request.getA01() + request.getB01();
        int C10 = request.getA10() + request.getB10();
        int C11 = request.getA11() + request.getB11();

        // Build the response with the result of matrix addition
        MatrixReply response = MatrixReply.newBuilder()
                .setC00(C00).setC01(C01)
                .setC10(C10).setC11(C11)
                .build();

        // Send the response to the client
        replyObserver.onNext(response);
        replyObserver.onCompleted();
    }

    @Override
    public void multiplyBlock(MatrixRequest request, StreamObserver<MatrixReply> replyObserver) {
        System.out.println("Request received for matrix multiplication:\n" + request);

        // Perform matrix multiplication for the specified block
        int C00 = request.getA00() * request.getB00() + request.getA01() * request.getB10();
        int C01 = request.getA00() * request.getB01() + request.getA01() * request.getB11();
        int C10 = request.getA10() * request.getB00() + request.getA11() * request.getB10();
        int C11 = request.getA10() * request.getB01() + request.getA11() * request.getB11();

        // Build the response with the result of matrix multiplication
        MatrixReply response = MatrixReply.newBuilder()
                .setC00(C00).setC01(C01)
                .setC10(C10).setC11(C11)
                .build();

        // Send the response to the client
        replyObserver.onNext(response);
        replyObserver.onCompleted();
    }
}
